#import <Cordova/CDV.h>
#import "ActivityIndicator.h"
#import "MBProgressHUD.h"

@implementation ActivityIndicator
@synthesize activityIndicator;

/**
 * This show the ProgressDialog
 */
- (void)show:(CDVInvokedUrlCommand*)command
{
    NSString* text = [command.arguments objectAtIndex:0];
    NSString* detailText = [command.arguments objectAtIndex:1];

    dispatch_async(dispatch_get_main_queue(), ^{

        self.activityIndicator = nil;
        self.activityIndicator = [MBProgressHUD showHUDAddedTo:self.webView.superview animated:YES];
        self.activityIndicator.mode = MBProgressHUDModeIndeterminate;
        self.activityIndicator.label.text = text;
        self.activityIndicator.label.textColor = [UIColor whiteColor];
        self.activityIndicator.detailsLabel.text = detailText;
        self.activityIndicator.detailsLabel.textColor = [UIColor whiteColor];
        self.activityIndicator.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;
        self.activityIndicator.bezelView.backgroundColor = [UIColor clearColor];
        self.activityIndicator.activityIndicatorColor = [UIColor whiteColor];

        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    });
}

/**
 * This hide the ProgressDialog
 */
- (void)hide:(CDVInvokedUrlCommand*)command
{
    dispatch_async(dispatch_get_main_queue(), ^{

        if (!self.activityIndicator) {
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
            return;
        }
        [self.activityIndicator hideAnimated:YES];
        self.activityIndicator = nil;
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    });
}

@end
