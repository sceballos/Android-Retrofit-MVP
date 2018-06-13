# Android Retrofit MVP
Sample app using retrofit and MVP  
This app is a simple master-detail flow witch loads data from https://randomuser.me/documentation api.  
Since the detail screen for the user has a Mapview, its mandatory to configure Google Maps service inside Google Cloud Console.  
You need to get a new api key and add this app with your debug/release keystore SHA1 in order to have a visible map.  
You can find a full explanation on how to do this here : https://developers.google.com/maps/documentation/android-api/start  
Once you have your api key, go to app/{app-package-name}/res/valus/strings.xml and replace the current api key with the new one that you have created.  

