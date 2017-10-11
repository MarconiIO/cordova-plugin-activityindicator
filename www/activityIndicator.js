var ActivityIndicator = {
    show: function (title, message) {
        title = title || "";
    	message = message || "";
        cordova.exec(null, null, "ActivityIndicator", "show", [title, message]);
    },
    hide: function () {
        cordova.exec(null, null, "ActivityIndicator", "hide", []);
    }
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = ActivityIndicator;
}
