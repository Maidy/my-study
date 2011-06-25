// 
//  Fugitive.m
//  iBountyHunter
//
//  Created by suguni on 11. 6. 25..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "Fugitive.h"


@implementation Fugitive 

@dynamic bounty;
@dynamic capturedLat;
@dynamic captured;
@dynamic capturedLon;
@dynamic image;
@dynamic fugitiveID;
@dynamic captdate;
@dynamic desc;
@dynamic name;


- (CLLocationCoordinate2D) coordinate {
	CLLocationCoordinate2D coord;
	coord.latitude = [self.capturedLat doubleValue];
	coord.longitude = [self.capturedLon doubleValue];
	return coord;
}

- (NSString *)title {
	return self.name;
}

- (NSString *)subtitle {
	return self.desc;
}

@end
