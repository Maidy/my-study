//
//  CapturedPhotoViewController.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 20..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import "Fugitive.h"

@interface CapturedPhotoViewController : UIViewController <UIImagePickerControllerDelegate, UINavigationControllerDelegate, UIActionSheetDelegate> {
	Fugitive *fugitive;
	UIImageView *fugitiveImage;
	MKMapView *fugitiveMapView;
}

@property (nonatomic, retain) Fugitive *fugitive;
@property (nonatomic, retain) IBOutlet UIImageView *fugitiveImage;
@property (nonatomic, retain) IBOutlet MKMapView *fugitiveMapView;

- (IBAction)doneTouchUp:(id)sender;
- (IBAction)takePictureButton:(id)sender;

@end
