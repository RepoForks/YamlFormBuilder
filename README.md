# YamlFormBuilder

You build form easliy, just write a yaml file!

```yaml
config:
  version: 1
form:
 - key: issue_type
   type: selector
   label: "Type of issue"
   params:
     values:
      - Enhancement
      - Bug
      - Feature request
      - Crash
     mode: simple
 - key: "title"
   type: textfield
   label: "Issue title"
   params:
    hint: "Title"
    max: 100
 - key: "device"
   type: textfield
   label: "Device"
   params:
    hint: "Device"
    max: 100
 - key: title
   type: selector
   label: "Select android version"
   params:
     values:
      - Android 4.3
      - Android 4.4
      - Android 5.0
      - Android 6.0
     mode: simple
 - key: "content"
   type: textfield
   label: "Content"
   params:
    hint: "Content"
    multiline: true
    extras:
      emojis: true
      images: true
      source: true
 - key: "status"
   type: checkbox
   label: "Status"
   params:
    hint: "Status"
    checked: true
```

And initialize yout fragment:

```java
public class SampleFormFragment extends YamlFormFragment {
  @Override
  protected InputStream getFormInputStream() {
    return getResources().openRawResource(R.raw.form);
  }
}
```

And that's it!

![Form](art/yamlreader.png)
