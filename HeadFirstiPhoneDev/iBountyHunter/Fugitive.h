//
//  Fugitive.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 18..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <CoreData/CoreData.h>


@interface Fugitive :  NSManagedObject  
{
}

@property (nonatomic, retain) NSNumber * captured;
@property (nonatomic, retain) NSDate * captdate;
@property (nonatomic, retain) NSDecimalNumber * bounty;
@property (nonatomic, retain) NSString * name;
@property (nonatomic, retain) NSNumber * fugitiveID;
@property (nonatomic, retain) NSString * desc;

@end



