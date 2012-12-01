//
//  WonderfulNumber.m
//  TextApp
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "WonderfulNumber.h"

@implementation WonderfulNumber

- (id)init
{
    // 책과 다른 부분. [super init]을 호출한 결과를 self에 할당하던지, 결과로 return 해야 한다고 함. 아니면 컴파일 에러
    // ARC 지원하면 self에 할당해야 함.
    self = [super init];
    
    storedNumber = 42.0;
    
    NSLog(@"A WonderfulNumber object was initialized!");
    
    return self;
}

- (void)dealloc
{
    NSLog(@"A WonderfulNumber object was deallocated!");
    
    // ARC에서는 dealloc 하면 안됨. scope가 벗어나면 자동으로 dealloc 됨.
//    [super dealloc];
}

- (void)setStoredNumber:(float)newNumber
{
    storedNumber = newNumber;
}

- (float)storedNumber
{
    return storedNumber;
}



@end
