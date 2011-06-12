//
//  FugitiveListViewController.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 12..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface FugitiveListViewController : UITableViewController {
	NSMutableArray* items;
}

@property (nonatomic, retain) NSMutableArray* items;

@end
