//
//  RootViewController.m
//  DrinkMixer
//
//  Created by suguni on 11. 5. 28..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "RootViewController.h"
#import "DrinkDetailViewController.h"
#import "AddDrinkViewController.h"
#import "DrinkConstants.h"

@implementation RootViewController

@synthesize drinks;
@synthesize addButtonItem;

#pragma mark -
#pragma mark View lifecycle

- (IBAction)addButtonItemPressed:(id)sender {
	
	AddDrinkViewController *controller = [[AddDrinkViewController alloc]
													  initWithNibName:@"DrinkDetailViewController" bundle:nil];
	[controller setDrinks:drinks];
	UINavigationController *nav = [[UINavigationController alloc]
								   initWithRootViewController:controller];
	
	[self presentModalViewController:nav animated:YES];
	
	[controller release];
	[nav release];
}

- (IBAction)editButtonItemPressed:(id)sender {
	
}

- (void)viewDidLoad {
    [super viewDidLoad];
	
	NSString* path = [[NSBundle mainBundle] pathForResource:@"DrinkDirections" ofType:@"plist"];
	NSMutableArray* tmp = [[NSMutableArray alloc] initWithContentsOfFile:path];
	self.drinks = tmp;
	[tmp release];
	
	[[NSNotificationCenter defaultCenter]
	 addObserver:self selector:@selector(applicationWillTerminate:)
	 name:UIApplicationWillTerminateNotification object:nil];

	self.navigationItem.rightBarButtonItem = self.addButtonItem;
	self.navigationItem.leftBarButtonItem = self.editButtonItem;
}

- (void)applicationWillTerminate:(NSNotification*)notif {
	NSLog(@"Application Will Terminate Notofication fired");
	NSString* path = [[NSBundle mainBundle] pathForResource:@"DrinkDirections" ofType:@"plist"];
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
    return [self.drinks count];
}


// Customize the appearance of table view cells.
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier] autorelease];
    }
    
	// Configure the cell.
	// cell.textLabel.text = [self.drinks objectAtIndex:indexPath.row];
	cell.textLabel.text = [[self.drinks objectAtIndex:indexPath.row] objectForKey:NAME_KEY];
	cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;

    return cell;
}


// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}

// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
		[self.drinks removeObjectAtIndex:indexPath.row];
		
        // Delete the row from the data source.
		[tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
    }   
}

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
	
	NSMutableDictionary* drink = [drinks objectAtIndex:indexPath.row];
	
	if (self.editing) {
		AddDrinkViewController *controller = [[AddDrinkViewController alloc]
											  initWithNibName:@"DrinkDetailViewController" bundle:nil];
		controller.drink = drink;
		controller.drinks = drinks;
		
		UINavigationController *nav = [[UINavigationController alloc]
									   initWithRootViewController:controller];
		
		[self presentModalViewController:nav animated:YES];
		
		[controller release];
		[nav release];
	} else {
		
		DrinkDetailViewController *detailViewController = [[DrinkDetailViewController alloc]
														   initWithNibName:@"DrinkDetailViewController" bundle:nil];
		detailViewController.drink = drink;
		
		// Pass the selected object to the new view controller.
		[self.navigationController pushViewController:detailViewController animated:YES];
		[detailViewController release];
	}
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
	[addButtonItem release];
    [super dealloc];
}


@end

