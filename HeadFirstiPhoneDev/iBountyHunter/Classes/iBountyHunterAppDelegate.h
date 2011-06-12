//
//  iBountyHunterAppDelegate.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 12..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>

@interface iBountyHunterAppDelegate : NSObject <UIApplicationDelegate> {
    
    UIWindow *window;
	IBOutlet UITabBarController *tabController;
    
@private
    NSManagedObjectContext *managedObjectContext_;
    NSManagedObjectModel *managedObjectModel_;
    NSPersistentStoreCoordinator *persistentStoreCoordinator_;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UITabBarController *tabController;

@property (nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext;
@property (nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel;
@property (nonatomic, retain, readonly) NSPersistentStoreCoordinator *persistentStoreCoordinator;

- (NSURL *)applicationDocumentsDirectory;
- (void)saveContext;
- (void)createEditableCopyOfDatabaseIfNeeded;

@end

