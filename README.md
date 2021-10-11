# Yes ImgUr

Yes ImgUr is an Android application that uses [ImgUr](https://apidocs.imgur.com/) APIs to allow users to get notified periodically about new Albums uploaded in the Gallery relating to the search they're interested in



#### Functional Requirements:

- The user should be able to Signup/Login using the ImgUr account.
- The user should be able to logout of the app.
- The user should be able to search in the ImgUr Gallery.
- The User should be able to favorite/unfavorite a search query.
- The User should be able see the list of favorite search queries.
- The User should be able to search using a favorite search query.
- The User should get periodic notifications about news Posts in their favorite queries.
- The User should land on the favorite search list page when click on the notification.



#### Technical Requirements and Constraints:

- In the event of Access Token expiring, use Refresh Token to reauthenticate the app.
- Clear data whenever the User logouts of the app deliberately. However, if the logout happens because of the auth failure, keep the data.
- Cache the data to save bandwidth. Use LRU for cache eviction.
- Save list of favorite search terms locally.
- Use pagination in list.
- Run Background sync for favorite search terms.
- Optimize images for screen size before caching and showing to save storage space and memory.
- Remove videos from the result
- Handle errors (eg. no internet, server errors)
- Instrument app start-up time



#### Hight Level Components:

- Entity
- Model
- Local Storage (Database, file system, cache and Shared Preferences)
- Storage Optimizer (LRU eviction)
- Network Service
- Repository
- View-Model
- View
- Background Sync Service (Manager and Worker)
- Notification Service
- Notification Handler
- Storage Cleaner
- Authorization Manager
- Dependency Injectors (Components and Modules)
- Image Optimizer