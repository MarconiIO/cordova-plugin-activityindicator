var ActivityIndicator = {
    show: function (text) {
        title = title || "";
    	message = message || "";
        cordova.exec(null, null, "ActivityIndicator", "show", [title, message]);
    },
    hide: function () {
        cordova.exec(null, null, "ActivityIndicator", "hide", []);
    }
};


var ActivityIndicatorBrowser = {
    show: function (text) {
        text = text || "Please wait...";
        console.log('ActivityIndicator show(): ' + text);
    },
    hide: function () {
        console.log('ActivityIndicator hide()');
    }
};

if (cordova.platformId === 'browser') {
    ActivityIndicator = ActivityIndicatorBrowser;
}

if (typeof module != 'undefined' && module.exports) {
    module.exports = ActivityIndicator;
}
