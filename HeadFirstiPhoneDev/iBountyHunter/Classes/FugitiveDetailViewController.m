//
//  FugitiveDetailViewController.m
//  iBountyHunter
//
//  Created by suguni on 11. 6. 13..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "FugitiveDetailViewController.h"
#import "Fugitive.h"

@implementation FugitiveDetailViewController

@synthesize fugitive, nameLabel, idLabel, descTextView, bountyLabel;

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

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	if (self.fugitive != nil) {
		nameLabel.text = fugitive.name;
		idLabel.text = [fugitive.fugitiveID stringValue];
		descTextView.text = fugitive.desc;
		bountyLabel.text = [fugitive.bounty stringValue];
	}
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
	
    [super dealloc];
}


@end
