//
//  Fugitive.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 20..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <CoreData/CoreData.h>


@interface Fugitive :  NSManagedObject  
{
}

@property (nonatomic, retain) NSDecimalNumber * bounty;
@property (nonatomic, retain) NSNumber * captured;
@property (nonatomic, retain) NSData * image;
@property (nonatomic, retain) NSNumber * fugitiveID;
@property (nonatomic, retain) NSDate * captdate;
@property (nonatomic, retain) NSString * desc;
@property (nonatomic, retain) NSString * name;

@end



