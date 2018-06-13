# Android Retrofit MVP
Sample app using retrofit and MVP  
This app is a simple master-detail flow witch loads data from https://randomuser.me/ api.  
Since the detail screen for the user has a Mapview, its mandatory to configure Google Maps service inside Google Cloud Console.  
You need to get a new api key and add this app with your debug/release keystore SHA1 in order to have a visible map.  
You can find a full explanation on how to do this here : https://developers.google.com/maps/documentation/android-api/start  
Once you have your api key, go to app/{app-package-name}/res/valus/strings.xml and replace the current api key with the new one that you have created.  

You can find the next package structure in the project

{app-package-name}  
		--activity  
		--adapter  
		--data  
		--interfaces  
		--network  
		--util  

-**Activity** : Holds every activity of the app. Inside you can fin two more packages (main, details) that holds the logic
for each screen (Contracts, Presenter implementations, Interactors and the activity file itself). In this case only  MainActivity.java needs MVP logic.  

-**Adapter** : Holds adapters for the lists of the app. In this case the only adapter used is UsersAdapters.java that manages the MainActivity.java RecyclerView.  

-**data** : Contains data structures used acorss the app.  

-**interfaces** : Contains retrofit based interfaces that request data from specific endpoints. If a new api needs to be added a new interface needs to be created to handle it.  

-**network** : Contains retrofit instances for each api to be consumed. If a new api needs to be added a new retrofit instance class needs to be created to handle it.  

-**util** : Any usefull tool needs to be placed here.