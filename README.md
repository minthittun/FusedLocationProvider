# FusedLocationProvider Tutorial With Helper Class (LocationProvider.java)

#### Android Mainfest
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

#### Dependencies
```
dependencies{
  implementation 'com.google.android.gms:play-services-base:16.0.1'
  implementation 'com.google.android.gms:play-services-location:16.0.0'
}
```
#### Bagan Application
```
https://www.dropbox.com/s/5pgipxyd9zr1a4t/bagan_v_1_0.apk?dl=0
```
```
(MM3)
Bagan Application သည် မည်သူတစ်ဦးတစ်ယောက်၏ အကျိုးအမြတ်အတွက်မျှ ရေးသားခြင်းမဟုတ်ပါ။ ပုဂံရှိ အချို့သော စေတီပုထိုးများ၏ အချက်အလတ်များကို သုံးစွဲသူများဖတ်ရှုလေ့လာနိုင်ရန်၊ ဘုရားများ၏ တည်နေရာများကိုသိရှိနိုင်စေရန်နှင့် နည်းပညာ(Programming) ကို လေ့လာနေသူများလည်း Bagan Application တွင် အသုံးပြုထားသော အချို့သော အသုံးဝင်နည်းပညာများကို လေ့လာနိုင်ရန် ရည်ရွယ်ခြင်းဖြစ်ပါသည်။ Bagan Application တွင် ကြော်ငြာများမပါဝင်ပါ။ အသုံးပြုသူများ၏ ကိုယ်ရေးအချက်အလတ်များ သိမ်းဆည်းထားခြင်းမရှိပါ။ ပါဝင်သော သတင်းအချက်အလတ်များသည် ဗဟုသုတ ရရှိရန်အတွက်သာ ရည်ရွယ်ခြင်းဖြစ်သည်။ စေတီပုထိုးများ၏ အချက်အလတ်များကို Wikipedia မှ ကူးယူတင်ပြထားပါသည်။ အကြောင်းတစ်စုံတစ်ရာရှိပါက baganlab.mtt@gmail.com သို့ ဆက်သွယ်မေးမြန်းနိုင်ပါသည်။
```
```
(ZG)
Bagan Application သည္ မည္သူတစ္ဦးတစ္ေယာက္၏ အက်ိဳးအျမတ္အတြက္မွ် ေရးသားျခင္းမဟုတ္ပါ။ ပုဂံရွိ အခ်ိဳ႕ေသာ ေစတီပုထိုးမ်ား၏ အခ်က္အလတ္မ်ားကို သံုးစြဲသူမ်ားဖတ္႐ွဳေလ့လာႏိုင္ရန္၊ ဘုရားမ်ား၏ တည္ေနရာမ်ားကိုသိရွိႏိုင္ေစရန္ႏွင့္ နည္းပညာ(Programming) ကို ေလ့လာေနသူမ်ားလည္း Bagan Application တြင္ အသံုးျပဳထားေသာ အခ်ိဳ႕ေသာ အသံုးဝင္နည္းပညာမ်ားကို ေလ့လာႏိုင္ရန္ ရည္ရြယ္ျခင္းျဖစ္ပါသည္။ Bagan Application တြင္ ေၾကာ္ျငာမ်ားမပါဝင္ပါ။ အသံုးျပဳသူမ်ား၏ ကိုယ္ေရးအခ်က္အလတ္မ်ား သိမ္းဆည္းထားျခင္းမရွိပါ။ ပါဝင္ေသာ သတင္းအခ်က္အလတ္မ်ားသည္ ဗဟုသုတ ရရွိရန္အတြက္သာ ရည္ရြယ္ျခင္းျဖစ္သည္။ ေစတီပုထိုးမ်ား၏ အခ်က္အလတ္မ်ားကို Wikipedia မွ ကူးယူတင္ျပထားပါသည္။ အေၾကာင္းတစ္စံုတစ္ရာရွိပါက baganlab.mtt@gmail.com သို႔ ဆက္သြယ္ေမးျမန္းႏိုင္ပါသည္။
```
