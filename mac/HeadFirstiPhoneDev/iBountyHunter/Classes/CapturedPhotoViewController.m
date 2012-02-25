//
//  CapturedPhotoViewController.m
//  iBountyHunter
//
//  Created by suguni on 11. 6. 20..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "CapturedPhotoViewController.h"


@implementation CapturedPhotoViewController

@synthesize fugitive, fugitiveImage, fugitiveMapView;

- (IBAction)doneTouchUp:(id)sender {
	[self dismissModalViewControllerAnimated:YES];
}

- (IBAction)takePictureButton:(id)sender {
	
	if ([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
		NSLog(@"This device has a camera, ask the user what they want to do.");
		UIActionSheet *photoSourceSheet = [[UIActionSheet alloc] initWithTitle:@"Select Fugitive Picture"
																	  delegate:self
															 cancelButtonTitle:@"Cancel"
														destructiveButtonTitle:nil
															 otherButtonTitles:@"Take New Photo", @"Choose Existing Photo", nil, nil];
		[photoSourceSheet showInView:self.view];
		[photoSourceSheet release];
	} else {
		UIImagePickerController* picker = [[UIImagePickerController alloc] init];
		picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
		picker.delegate = self;
		picker.allowsEditing = YES;
		[self presentModalViewController:picker animated:YES];
	}
}

- (void)actionSheet:(UIActionSheet *)actionSheet didDismissWithButtonIndex:(NSInteger)buttonIndex {

	NSLog(@"Taking a picture.");
	
	UIImagePickerController* picker = [[UIImagePickerController alloc] init];
	picker.delegate = self;
	picker.allowsEditing = YES;
	

	switch (buttonIndex) {
		case 0:
			picker.sourceType = UIImagePickerControllerSourceTypeCamera;
			break;
		case 1:
			picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
			break;
		default:
			break;
	}
	
	[self presentModalViewController:picker animated:YES];
	
}

- (void)imagePickerController:(UIImagePickerController *)picker
		didFinishPickingImage:(UIImage *)image
		editingInfo:(NSDictionary *)editingInfo {
	
	self.fugitive.image = UIImagePNGRepresentation(image);
	[self dismissModalViewControllerAnimated:YES];
	[picker release];
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker {
	[self dismissModalViewControllerAnimated:YES];
	[picker release];
}

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	self.fugitiveImage.image = [[[UIImage alloc] initWithData:fugitive.image] autorelease];
	
	if ([fugitive.captured boolValue] == YES) {
		CLLocationCoordinate2D mapCenter;
		mapCenter.latitude = [fugitive.capturedLat doubleValue];
		mapCenter.longitude = [fugitive.capturedLon doubleValue];
		
		MKCoordinateSpan mapSpan;
		mapSpan.latitudeDelta = 0.005;
		mapSpan.longitudeDelta = 0.005;
		
		MKCoordinateRegion region;
		region.center = mapCenter;
		region.span = mapSpan;
		
		fugitiveMapView.region = region;
	}
	fugitiveMapView.mapType = MKMapTypeHybrid;
	[fugitiveMapView addAnnotation:fugitive];
	
}

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
	[fugitiveImage release];
	[fugitiveMapView release];
    [super dealloc];
}


@end
