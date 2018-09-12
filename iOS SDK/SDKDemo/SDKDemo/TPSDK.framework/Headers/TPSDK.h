//
//  TPSDK.h
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/6.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <UIKit/UIKit.h>

//! Project version number for TPSDK.
FOUNDATION_EXPORT double TPSDKVersionNumber;

//! Project version string for TPSDK.
FOUNDATION_EXPORT const unsigned char TPSDKVersionString[];

/**
 * Add this macro before each category implementation, so we don't have to use
 * -all_load or -force_load to load object files from static libraries that only contain
 * categories and no classes.
 * See http://developer.apple.com/library/mac/#qa/qa2006/qa1490.html for more info.
 */
#define TT_FIX_CATEGORY_BUG(name) @interface TT_FIX_CATEGORY_BUG_##name @end \
@implementation TT_FIX_CATEGORY_BUG_##name @end

#import <TPSDK/TPApi.h>
#import <TPSDK/TPReqObj.h>
#import <TPSDK/TPRespObj.h>
