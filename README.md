Update Dec 15, 2015: Fixed.

## HELP PLEASE!

I am trying to create a floating view (using WindowManager) that uses EditText Component. But here's the issue.

If I create the floating view using the `FLAG_NOT_FOCUSABLE`, the keyboard doesn't open up when I focus on the Edittext Component by touching on it.

I tried removing the `FLAG_NOT_FOCUSABLE` flag, and the keyboard shows up just fine. It works as expected. BUT. A BIG BUT. Not "Butt", but "But". But, the back button is broken now. Not only back button, but all of the touch screen. I am not able to touch the screen anywhere except the floating view. :( (I want to touch it everywhere!)

What can we do? Please help.

### How to use this repo?

Clone it.

In file, `EditTextService.java`, there are two methods that return the LayoutParams for the floating view: `getParamsThatWorksForTouchEvents()` and `getParamsThatWorksForKeyboard()`.

In `onCreate()` method, these methods are called using this section:

```java
WindowManager.LayoutParams params = this.getParamsThatWorksForKeyboard();
// WindowManager.LayoutParams params = this.getParamsThatWorksForTouchEvents();
```

Comment out one of the line and the other one will give you the desired effect to reproduce the issue.

### Reward: A Big Hug. :)
