# p.20
# pydelicious가 좀 오래되서 지원안되는것 있음.
# get_popuplar()와 같이 문서화되어 있지 않은 http://del.icio.us/rss/ API를 사용하는 함수들은 사용 불가, 아래와 같이 변경해야 할 듯.

# pydelicious.get_popular(tag='programming') #=>
# pydelicious.dlcs_feed('popular_tagged', format='json', tag='programming')
# http://feeds.delicious.com/v2/json/popular/programming
# [{"a": "chuckreynolds",
#   "dt": "2011-10-11T14:00:00Z",
#   "u": "http://www.webmasterworld.com/forum92/4332.htm",
#   "d": "Mod_Rewrite and Regular Expressions Apache Web Server forum at WebmasterWorld",
#   "t": ["regex", "mod_rewrite", "htaccess", "apache", "regexp", "reference", "regular", "programming", "expressions", "webdev"]},
#   ...]

# pydelicious.get_urlposts('http://arcturo.github.com/library/coffeescript/index.html') #=>
# pydelicious.dlcs_feed('url', format='json', urlmd5=md5('http://arcturo.github.com/library/coffeescript/index.html').hexdigest())
# http://feeds.delicious.com/v2/json/url/664f010c4164c449ba8eab79259f14a7
# [{"a": "popeax",
#   "d": "The Little Book on CoffeeScript",
#   "n": "",
#   "u": "http://arcturo.github.com/library/coffeescript/index.html",
#   "t": ["coffeescript", "javascript", "programming", "web browser"],
#   "dt": "2011-10-11T07:31:05Z"},
#   ...]

# pydelicious.get_userposts('suguni') #=>
# pydelicious.dlcs_feed('user', format='json', username='suguni')
# http://feeds.delicious.com/v2/json/suguni
# [{"a": "suguni",
#   "d": "The Little Book on CoffeeScript",
#   "n": "",
#   "u": "http://arcturo.github.com/library/coffeescript/index.html",
#   "t": ["coffeescript", "programming", "tutorial", "development", "books", "ebook"],
#   "dt": "2011-10-07T09:54:20Z"},
#   ...]

from pydelicious import get_popular, get_userposts, get_urlposts

# popular post의 user 목록 만들기
def initializeUserDict(tag, count=5):
    user_dict = {}

    # get the top count' popular posts
    for p1 in get_popular(tag)[0:count]:
        # find all users who posted this
        for p2 in get_urlposts(p1['u']):
            user = p2['a']
            user_dict[user] = {}

    return user_dict

# user 별 url cross check, 있으면 1, 없으면 0
# 결국 user/url 표 만들기.
# recommendation.py에서 만든 critics와 동일하다. 단지 평점이 아닌 1, 0 값만 가진다.
def fillItems(user_dict):

    all_items = {}

    # Find links posted by all users
    for user in user_dict:
        print "finding ... " + user
        for i in range(3):
            try:
                posts = get_userposts(user)
                break
            except:
                print "Failed user " + user + ", retrying"
                time.sleep(4)

        for post in posts:
            url = post['u']
            user_dict[user][url] = 1.0
            all_items[url] = 1

    for ratings in user_dict.values():
        for item in all_items:
            if item not in ratings:
                ratings[item] = 0.0


