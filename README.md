# ImageSwitch

A library to give you Switch with image background + UI Sample by Jetpack Compose

## Give a Star! ⭐
If you like or are using this project to learn or start your solution, please give it a star. Thanks!

[![](https://jitpack.io/v/razaghimahdi/ImageSwitch.svg)](https://jitpack.io/#razaghimahdi/ImageSwitch)

### Step 1. Add it in your project-level `build.gradle` or `settings.gradle` file:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.razaghimahdi:ImageSwitch:1.0.0'
	} 

### Step 3. How to use

```kotlin
val checkedState = remember { mutableStateOf(true) }

ImageSwitch(
    checkedImage = painterResource(R.drawable.good_morning),
    unCheckedImage = painterResource(R.drawable.good_night),
    size = 150.dp,
    checked = checkedState.value,
    onCheckedChange = { checkedState.value = it }
)
```


https://user-images.githubusercontent.com/61207818/226142337-db631f05-9f8e-40a0-8c54-da3f533de99d.mp4







Developed by Mahdi Razzaghi Ghaleh
