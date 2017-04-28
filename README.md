# navigator-demo
Using Navigator library to rewrite the Conductor Demo App.

## Notable Libraries Used
 * [Anko](https://github.com/Kotlin/anko) - DSL to write layouts in code rather than in xml
 * [Simple Stack](https://github.com/Zhuinden/simple-stack) - backstack library for simpler navigation between views, fragments, or whatevers

## Anko
 * **Pros**
   * Easier to create layouts through DSL - *No xmls. Yay?* 
   * [Faster](https://nethergrim.github.io/performance/2016/04/16/anko.html)
   * No more `findViewById` or `ButterKnife`
 * **Cons**
   * No preview (they have a plugin, but I can't make it work)
   * Styles - Styling is not really hard if you create your own, but when using appcompat styles, I needed to look for it's exact styles to mimic them

 - I really think kotlin android extensions is better in terms of ease of use but programmatic views are faster and you wouldn't need to code in xml anymore
 
## Simple Stack
 * **Pros**
   * It's **simple**. Like a simplified flow.
   * You have granular control on your lifecycle. *Double edged. But I prefer it, all bugs could be blamed by me.*
   * Screens are just ViewGroups
 * **Cons**
   * Multiple Child view with backstack support is hard but I don't think there's a lot of use case for it. Did not continue to implement `Multiple Child Routers` like in the Conductor demo as it was too hard and I felt like hacking already

- Simple Stack is nice and simple and I prefer it over Flow or Conductor. Flow is complex and Conductor, I'm just not sure on what happens internally on its lifecycle and I wanted to avoid that like how I'm avoiding fragments now.

## Notes
The building blocks for the UI is just extending
 * [ViewKey](https://github.com/gumil/navigator-demo/blob/master/app/src/main/kotlin/io/github/gumil/testnavigator/common/ViewKey.kt)
   * This interface has lifecycle callbacks and it's the key to use to add to the backstack.
 * [ViewLayout](https://github.com/gumil/navigator-demo/blob/master/app/src/main/kotlin/io/github/gumil/testnavigator/common/ViewLayout.kt)
   * This abstract class is the `xml`. Everything UI or View related is here.
 * [ViewStateChanger](https://github.com/gumil/navigator-demo/blob/master/app/src/main/kotlin/io/github/gumil/testnavigator/common/ViewStateChanger.kt)
   * The code I copied mostly from the DefaultStateChanger of Simple Stack. I wanted to support the ViewLayout class instead of inflating from xml. Lifecycle callbacks are called here, we have full control of the lifecycle here.
   
