//
//  Fugitive.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 25..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <CoreData/CoreData.h>
#import <MapKit/MapKit.h>

@interface Fugitive :  NSManagedObject <MKAnnotation>
{
}

@property (nonatomic, retain) NSDecimalNumber * bounty;
@property (nonatomic, retain) NSNumber * capturedLat;
@property (nonatomic, retain) NSNumber * captured;
@property (nonatomic, retain) NSNumber * capturedLon;
@property (nonatomic, retain) NSData * image;
@property (nonatomic, retain) NSNumber * fugitiveID;
@property (nonatomic, retain) NSDate * captdate;
@property (nonatomic, retain) NSString * desc;
@property (nonatomic, retain) NSString * name;

#pragma mark -
#pragma mark MapKit Annotation Protocol

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;

- (NSString *) title;
- (NSString *) subtitle;

@end



