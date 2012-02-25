//
//  FugitiveDetailViewController.m
//  iBountyHunter
//
//  Created by suguni on 11. 6. 13..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "FugitiveDetailViewController.h"
#import "Fugitive.h"
#import "CapturedPhotoViewController.h"

@implementation FugitiveDetailViewController

@synthesize fugitive, nameLabel, idLabel, descTextView, bountyLabel;
@synthesize capturedDateLabel, capturedToggle, capturedLatLonLabel, locationManager;

// The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
/*
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization.
    }
    return self;
}
*/

/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations.
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/


- (void)locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation {
	capturedToggle.enabled = YES;
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
	capturedToggle.enabled = NO;
}

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	// init location manager
	self.locationManager = [[CLLocationManager alloc] init];
	self.locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters;
	self.locationManager.delegate = self;
	[self.locationManager startUpdatingLocation];
	
	if (self.fugitive != nil) {
		nameLabel.text = fugitive.name;
		idLabel.text = [fugitive.fugitiveID stringValue];
		descTextView.text = fugitive.desc;
		bountyLabel.text = [fugitive.bounty stringValue];
		capturedDateLabel.text = [fugitive.captdate description];
		capturedToggle.selectedSegmentIndex = [fugitive.captured boolValue] ? 0 : 1;
		capturedLatLonLabel.text = [NSString stringWithFormat:@"%.3f %.3f",
									[fugitive.capturedLat doubleValue],
									[fugitive.capturedLon doubleValue]];
	}
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
	[locationManager stopUpdatingLocation];
	locationManager = nil;
}

- (IBAction)capturedToggleChanged:(id)sender {
	if ([capturedToggle selectedSegmentIndex] == 0) {
		NSDate *now = [NSDate date];
		fugitive.captdate = now;
		fugitive.captured = [NSNumber numberWithBool:YES];
		
		// set location
		CLLocation *loc = self.locationManager.location;
		fugitive.capturedLat = [NSNumber numberWithDouble:loc.coordinate.latitude];
		fugitive.capturedLon = [NSNumber numberWithDouble:loc.coordinate.longitude];
		
	} else {
		fugitive.captdate = nil;
		fugitive.captured = [NSNumber numberWithBool:NO];
		fugitive.capturedLat = nil;
		fugitive.capturedLon = nil;
	}
	capturedDateLabel.text = [fugitive.captdate description];
	capturedLatLonLabel.text = [NSString stringWithFormat:@"%.3f %.3f",
								[fugitive.capturedLat doubleValue],
								[fugitive.capturedLon doubleValue]];
}

- (IBAction)infoTouchUp:(id)sender {
	CapturedPhotoViewController* controller = [[CapturedPhotoViewController alloc]
											   initWithNibName:@"CapturedPhotoViewController"
											   bundle:nil];

	controller.fugitive = self.fugitive;
	[controller setModalTransitionStyle:UIModalTransitionStyleFlipHorizontal];
	[self presentModalViewController:controller animated:YES];
	
	[controller release];
}

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc. that aren't in use.
}

- (void)viewDidUnload {
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[fugitive release];
	[nameLabel release];
	[idLabel release];
	[descTextView release];
	[bountyLabel release];
	[capturedDateLabel release];
	[capturedToggle release];
	[capturedLatLonLabel release];
	[locationManager release];
	
    [super dealloc];
}

@end
