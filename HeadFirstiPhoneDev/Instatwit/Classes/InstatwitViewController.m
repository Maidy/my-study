//
//  InstatwitViewController.m
//  Instatwit
//
//  Created by suguni on 11. 5. 22..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "InstatwitViewController.h"

@implementation InstatwitViewController

@synthesize tweetPicker;

- (IBAction) sendButtonTapped:(id)sender {
	NSString* themessage = [NSString stringWithFormat:@"I'm %@ and feeling %@ about it.",
					 [activities objectAtIndex:[tweetPicker selectedRowInComponent:0]],
					 [feelings objectAtIndex:[tweetPicker selectedRowInComponent:1]]];
//	NSLog(@"Tweet It! button tapped.");
//	NSLog(themessage);
	
	NSMutableURLRequest *theRequest = [NSMutableURLRequest
									   requestWithURL:[NSURL URLWithString:@"http:suguni:kdjtx337@twitter.com/statuses/update.xml"]
									   cachePolicy:NSURLRequestUseProtocolCachePolicy
									   timeoutInterval:60.0];
	[theRequest setHTTPMethod:@"POST"];
	[theRequest setHTTPBody:[[NSString stringWithFormat:@"status=%@", themessage] dataUsingEncoding:NSASCIIStringEncoding]];
	NSURLResponse* response;
	NSError* error;
	NSData* result = [NSURLConnection sendSynchronousRequest:theRequest returningResponse:&response error:&error];
	NSLog(@"%@", [[[NSString alloc] initWithData:result encoding:NSASCIIStringEncoding] autorelease]);
}

- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
	return 2;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
	if (component == 0) {
		return [activities count];
	} else {
		return [feelings count];
	}
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
	if (component == 0) {
		return [activities objectAtIndex:row];
	} else {
		return [feelings objectAtIndex:row];
	}
	return nil;
}

/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
	activities = [[NSArray alloc] initWithObjects: @"sleeping", @"eating",
				  @"working", @"thinking", @"crying", @"begging", @"leaving",
				  @"shopping", @"hello worlding", nil];
	feelings = [[NSArray alloc] initWithObjects: @"awesome", @"sad",
				@"happy", @"ambivalent", @"nauseous", @"psyched", @"confused",
				@"hopeful", @"anxious", nil];
}


/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[tweetPicker release];
	[activities release];
	[feelings release];
    [super dealloc];
}

@end
