def readfile(filename):
    lines = [line for line in file(filename)]

    # First line is the column titles
    colnames = lines[0].strip().split('\t')[1:]
    rownames = []
    data = []

    for line in lines[1:]:
        p = line.strip().split('\t')

        # First column in each row is the rowname
        rownames.append(p[0])

        data.append([float(x) for x in p[1:]])

    return rownames, colnames, data
# ex:
# blognames, words, data = clusters.readfile('blogdata.org.txt')

from math import sqrt
def pearson(v1, v2):

    n = len(v1)
    
    # Simple sums
    sum1 = sum(v1)
    sum2 = sum(v2)

    sum1Sq = sum([pow(v, 2) for v in v1])
    sum2Sq = sum([pow(v, 2) for v in v2])

    pSum = sum([v1[i] * v2[i] for i in range(n)])

    num = pSum - (sum1 * sum2 / n)
    den = sqrt((sum1Sq - pow(sum1, 2) / n) * (sum2Sq - pow(sum2, 2) / n))

    if den == 0: return 0

    return 1.0 - num / den

class bicluster:
    def __init__(self, vec, left=None, right=None, distance=0.0, id=None):
        self.vec = vec
        self.left = left
        self.right = right
        self.distance = distance
        self.id = id


def hcluster(rows, distance=pearson):
    distances = {}
    currentclusterid = -1

    clust = [bicluster(rows[i], id=i) for i in range(len(rows))]

    while len(clust) > 1:
        print '.',
        lowestpair = (0, 1)
        closest = distance(clust[0].vec, clust[1].vec)

        for i in range(len(clust)):
            for j in range(i+1, len(clust)):
                if (clust[i].id, clust[j].id) not in distances:
                    distances[(clust[i].id, clust[j].id)] = distance(clust[i].vec, clust[j].vec)
                d = distances[(clust[i].id, clust[j].id)]

                if d < closest:
                    closest = d
                    lowestpair = (i, j)

        mergevec = [(clust[lowestpair[0]].vec[i] + clust[lowestpair[1]].vec[i]) / 2.0
                    for i in range(len(clust[0].vec))]

        newcluster = bicluster(vec=mergevec,
                               left=clust[lowestpair[0]],
                               right=clust[lowestpair[1]],
                               distance=closest,
                               id=currentclusterid)

        currentclusterid = -1
        del clust[lowestpair[1]]
        del clust[lowestpair[0]]
        clust.append(newcluster)

    return clust[0]

def printclust(clust, labels=None, n=0):
    for i in range(n): print ' ',
    if clust.id < 0:
        print '-'
    else:
        if labels == None: print clust.id
        else: print labels[clust.id]

    if clust.left != None: printclust(clust.left, labels=labels, n=n+1)
    if clust.right != None: printclust(clust.right, labels=labels, n=n+1)

from PIL import Image, ImageDraw

def getheight(clust):
    if clust.left == None and clust.right == None: return 1
    return getheight(clust.left) + getheight(clust.right)

def getdepth(clust):
    if clust.left == None and clust.right == None: return 0
    return getdepth(clust.left) + getdepth(clust.right) + clust.distance

def drawdendrogram(clust, labels, jpeg='clusters.jpg'):
    h = getheight(clust) * 20
    w = 1200
    depth = getdepth(clust)

    scaling = float(w - 150) / depth

    img = Image.new('RGB', (w, h), (255, 255, 255))
    draw = ImageDraw.Draw(img)

    draw.line((0, h/2, 10, h/2), fill=(255, 0, 0))

    drawnode(draw, clust, 10, (h/2), scaling, labels)
    img.save(jpeg, 'JPEG')

def drawnode(draw, clust, x, y, scaling, labels):
    if clust.id < 0:
        h1 = getheight(clust.left) * 20
        h2 = getheight(clust.right) * 20
        top = y - (h1 + h2) / 2
        bottom = y + (h1 + h2) / 2

        ll = clust.distance * scaling

        draw.line((x, top+h1/2, x, bottom-h2/2), fill=(255, 0, 0))
        draw.line((x, top+h1/2, x+ll, top+h1/2), fill=(255, 0, 0))
        draw.line((x, bottom-h2/2, x+ll, bottom-h2/2), fill=(255, 0, 0))

        drawnode(draw, clust.left, x+ll, top+h1/2, scaling, labels)
        drawnode(draw, clust.right, x+ll, bottom-h2/2, scaling, labels)
    else:
        draw.text((x+5, y-7), labels[clust.id], (0, 0, 0))

    
def rotatematrix(data):
    newdata = []
    for i in range(len(data[0])):
        newrow = [data[j][i] for j in range(len(data))]
        newdata.append(newrow)
    return newdata

import random

def kcluster(rows, distance=pearson, k=4):
    # Determine the minimum and maximum values for each point
    ranges = [(min(rows[r][i] for r in range(len(rows))),
               max(rows[r][i] for r in range(len(rows))))
              for i in range(len(rows[0]))]

    # Create k randomly placed centroids
    clusters = [[random.random() * (ranges[i][1] - ranges[i][0]) + ranges[i][0]
                 for i in range(len(rows[0]))]
                for j in range(k)]

    lastmatches = None
    for t in range(100):
        print 'Iteration %d' % t
        bestmatches = [[] for i in range(k)]

        for j in range(len(rows)):
            row = rows[j]
            bestmatch = 0

            for c in range(k):
                d = distance(clusters[c], row)
                if (d < distance(clusters[bestmatch], row)): bestmatch = c

            bestmatches[bestmatch].append(j)

        if bestmatches == lastmatches: break
        lastmatches = bestmatches

        # Move the centroids to the average of their members
        for i in range(k):
            avgs = [0.0] * len(rows[0])
            if len(bestmatches[i]) > 0:
                for rowid in bestmatches[i]:
                    for m in range(len(rows[rowid])):
                        avgs[m] += rows[rowid][m]
                for j in range(len(avgs)):
                    avgs[j] /= len(bestmatches[i])
                clusters[i] = avgs

    return bestmatches
# ex:
# kclust = clusters.kcluster(data, k=10)
                

    
