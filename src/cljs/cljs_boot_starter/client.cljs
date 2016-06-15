(ns cljs-boot-starter.client
  (:require [reagent.core :as ra :refer [atom render]]))

(enable-console-print!)

;; JavaScript Interop practices

;; from main.js----------------
;;(def textM js/globalVarFromMain)
;;(js/console.log textM)


;; from vendor/example01.js----
;;
(def textV1 js/globalVarEx01)
(js/console.log textV1)
(js/console.log js/globalVarEx01)

;; getting array values
(js/console.log (aget js/globalArray 0))
(js/console.log (aget js/globalArray 1))
(js/console.log (aget js/globalArray 2))
(js/console.log (aget js/globalArray 3))
(js/console.log (aget js/globalArray 4))
(js/console.log (aget js/globalArray 5)) ;;output: undefined, because 5th index is not in globalArray

;; getting nested element from Array
(js/console.log (aget js/globalArray 3 1))

;; getting object values
(js/console.log "Getting globalObject values--------->")
(js/console.log js/globalObject)
(js/console.log js/globalObject.a)
(js/console.log js/globalObject.c)
(js/console.log js/globalObject.d)


(js/console.log "invoking user define method/function------------>")
(.hello js/window)
;; another way to call global function
(js/window.helloAgain "I'm from helloAgain function which is user define function with passing argument")

(js/console.log (js/MyType.))

(def userFunction01 (js/MyComplexType. "I'm user type function with string argument"))
(js/console.log userFunction01)

(def myType (js/MyType.))
(def myTypeValue (.-name myType))
(js/console.log myTypeValue)

(js/console.log (.-name myType))
(js/console.log (aget myType "name"))
(js/console.log (aset myType "name" "Hi, I'm setting value in name"))
(js/console.log (set! (.-name myType) "Hi, Nishant"))

;; nested scopes (skiped)---------------------

;;(def m1 (new js/Microsoft.Maps.Themes.BingTheme))

;;(def m2 (js/Microsoft.Maps.Themes.BingTheme.))
;; -------------------------------------------

;;creating javascript objects

(def my-object (js-obj "a" 134 "b" true "c" nil))
(js/console.log my-object)

(def js-object (js-obj  :a 132 :b [1 2 3] :c #{"d" true :e nil}))
(js/console.log js-object)

(js/console.log (aget my-object "b"))

(js/console.log (aget js-object :a))

(def js-object02 (clj->js  {:a 13 :b [15 52 35] :c #{"d" true :e nil}}))
(js/console.log js-object02)
(js/console.log (aget js-object02 "b"))

;; ------------------------------------
(def my-array01 (js->clj (.-globalArray js/window)))
(js/console.log my-array01)
(js/console.log (get my-array01 2))
(def my-obj01 (js->clj (.-globalObject js/window)))
(js/console.log (get my-obj01 "d"))
(def my-obj02 (js->clj (.-globalObject js/window) :keywordize-keys true))
(js/console.log my-obj02)
(def a (:b my-obj02))
(def b (get-in my-obj02 [:c :e]))
(js/console.log (+ a b))



;; ----------------------------
;; from vendor/example02.js----

(def textV2 js/globalVarEx02)
(js/console.log textV2)

(defn hello []
  [:div
   "Hello world!"])

(defn init []
  (render [hello] (.getElementById js/document "my-app-area")))

(init)
