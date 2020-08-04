# EOS-Public-Chain

This application shows the 20 most recent blocks from EOS public chain.

- Public chain endpoint : https://eos.greymass.com/
- Minimum OS version: Lollipop (Android 5.0 SDK 21)
- Architecture Pattern: MVVM + Repository
- Language: Kotlin

### Main Screen
Load more button re-fetches the 20 most recent blocks from the chain.

![Screenshot](/docs/img/ss-main.png)

### Details Screen
The details screen displays the summary of a given block. By toggling See More switch, the raw data of the block is shown.

![Screenshot](/docs/img/ss-details.png)

## Dependencies
Dependency Injection : Hilt
Network : Retrofit
Json Formatting : Gson
UI: Material Design
