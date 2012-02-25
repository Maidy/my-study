//
//  RootViewController.m
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 2..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "RootViewController.h"
#import "DrinkDetailViewController.h"
#import "DrinkConstant.h"
#import "AddDrinkViewController.h"

@implementation RootViewController

@synthesize drinks;
@synthesize addButton;

#pragma mark -
#pragma mark View lifecycle

- (IBAction)addButtonPressed:(id)sender {
	NSLog(@"add button pressed");
	
	AddDrinkViewController *viewController = 
		[[AddDrinkViewController alloc]
		 initWithNibName:@"DrinkDetailViewController" bundle:nil];
	viewController.drinkArray = self.drinks;
	
	UINavigationController *navController =
		[[UINavigationController alloc]
		 initWithRootViewController:viewController];
	
	[self presentModalViewController:navController animated:YES];
	
	[navController release];
	[viewController release];
}

- (void)viewDidLoad {
    [super viewDidLoad];
	
	[[NSNotificationCenter defaultCenter] addObserver:self
											 selector:@selector(applicationDidEnterBackground:)
												 name:UIApplicationDidEnterBackgroundNotification
											   object:nil];
	
	NSString *path = [[NSBundle mainBundle] pathForResource:@"DrinksDirections" ofType:@"plist"];
	// NSURL *url = [[NSURL alloc] initWithScheme:@"http" host:@"www.yuiworld.kr" path:@"/test/DrinkArray.plist"];
	// drinks = [[NSArray alloc] initWithContentsOfURL:url];
	drinks = [[NSMutableArray alloc] initWithContentsOfFile:path];

    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
	self.navigationItem.rightBarButtonItem = self.addButton;
}

- (void)applicationDidEnterBackground:(NSNotification*)noti {
	NSString *path = [[NSBundle mainBundle] pathForResource:@"DrinksDirections" ofType:@"plist"];
	[self.drinks writeToFile:path atomically:YES];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
	
	[self.tableView reloadData];
}

/*
- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
}
*/
/*
- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
}
*/
/*
- (void)viewDidDisappear:(BOOL)animated {
	[super viewDidDisappear:animated];
}
*/

/*
 // Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
	// Return YES for supported orientations.
	return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
 */


#pragma mark -
#pragma mark Table view data source

// Customize the number of sections in the table view.
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// Customize the number of rows in the table view.
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [drinks count];
}


// Customize the appearance of table view cells.
- (UITableViewCell *)tableView:(UITableView *)tableView
		 cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView
							 dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc]
				 initWithStyle:UITableViewCellStyleDefault
				 reuseIdentifier:CellIdentifier] autorelease];
    }
    
	// Configure the cell.
	// cell.textLabel.text = [drinks objectAtIndex:indexPath.row]; // crash!!!
	cell.textLabel.text = [[drinks objectAtIndex:indexPath.row] 
						   objectForKey:NAME_KEY];
	
	cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;

    return cell;
}


/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/


/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source.
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
    }   
}
*/


/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/


/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/


#pragma mark -
#pragma mark Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
	DrinkDetailViewController *detailViewController =
		[[DrinkDetailViewController alloc] initWithNibName:@"DrinkDetailViewController" bundle:nil];
	
	NSLog(@"alloc");
	
	detailViewController.drink = [drinks objectAtIndex:indexPath.row];

	NSLog(@"set drink");
	
	[self.navigationController pushViewController:detailViewController animated:YES];
	
	NSLog(@"pushViewController");
	
	[detailViewController release];
}


#pragma mark -
#pragma mark Memory management

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Relinquish ownership any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
    // Relinquish ownership of anything that can be recreated in viewDidLoad or on demand.
    // For example: self.myOutlet = nil;
	[[NSNotificationCenter defaultCenter] removeObserver:self];
}


- (void)dealloc {
	[drinks release];
	[addButton release];
    [super dealloc];
}


@end

