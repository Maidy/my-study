# p.20
# pydelicious가 좀 오래되서 지원안되는것 있음.
# get_popuplar()와 같이 문서화되어 있지 않은 http://del.icio.us/rss/ API를 사용하는 함수들은 사용 불가, 아래와 같이 변경해야 할 듯.

# pydelicious.get_popular(tag='programming') #=>
# pydelicious.dlcs_feed('popular_tagged', format='json', tag='programming')
# http://feeds.delicious.com/v2/json/popular/programming

# pydelicious.get_urlposts('http://arcturo.github.com/library/coffeescript/index.html') #=>
# pydelicious.dlcs_feed('url', format='json', urlmd5=md5('http://arcturo.github.com/library/coffeescript/index.html').hexdigest())
# http://feeds.delicious.com/v2/json/url/664f010c4164c449ba8eab79259f14a7

# pydelicious.get_userposts('suguni') #=>
# pydelicious.dlcs_feed('user', format='json', username='suguni')
# http://feeds.delicious.com/v2/json/suguni

from pydelicious import get_popular, get_userposts, get_urlposts

def initializeUserDict(tag, count=5):
    user_dict = {}

    # get the top count' popular posts
    for p1 in get_popular(tag)[0:count]:
        # find all users who posted this
        for p2 in get_urlposts(p1['u']):
            user = p2['a']
            user_dict[user] = {}

    return user_dict

