package com.alorma.yaml.formbuilder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;

public class YamlFormAdapter extends RecyclerArrayAdapter<YamlFormObject, YamlFormAdapter.FormHolder> {

  private static final int TYPE_DEFAULT = 0;
  private static final int TYPE_TEXTFIELD = 1;
  private static final int TYPE_SELECTOR = 2;
  private static final int TYPE_BIG_TEXTFIELD = 3;

  public YamlFormAdapter(LayoutInflater inflater) {
    super(inflater);
  }

  @Override
  protected FormHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
    FormHolder holder;
    switch (viewType) {
      case TYPE_TEXTFIELD:
        holder = new TextFieldFormHolder(inflater.inflate(R.layout.form_textfield, parent, false));
        break;
      case TYPE_BIG_TEXTFIELD:
        holder = new BigTextFieldFormHolder(inflater.inflate(R.layout.form_big_textfield, parent, false));
        break;
      case TYPE_SELECTOR:
        holder = new SelectorFieldFormHolder(inflater.inflate(R.layout.form_selector, parent, false));
        break;
      case TYPE_DEFAULT:
      default:
        holder = new EmptyFormHolder(new View(parent.getContext()));
    }
    return holder;
  }

  @Override
  protected int getItemViewType(YamlFormObject yamlFormObject) {
    switch (yamlFormObject.type) {
      case textfield:
        if (yamlFormObject.params.multiline) {
          return TYPE_BIG_TEXTFIELD;
        } else {
          return TYPE_TEXTFIELD;
        }
      case selector:
        return TYPE_SELECTOR;
      default:
        return TYPE_DEFAULT;
    }
  }

  @Override
  protected void onBindViewHolder(FormHolder FormHolder, YamlFormObject yamlFormObject) {
    FormHolder.fill(yamlFormObject.params);
  }

  private class SelectorFieldFormHolder extends FormHolder {

    private final Spinner spinner;

    SelectorFieldFormHolder(View itemView) {
      super(itemView);
      spinner = (Spinner) itemView.findViewById(R.id.spinner);
    }

    @Override
    public void fill(YamlFormItem formItem) {
      List<String> values = formItem.values;
      ArrayAdapter<String> adapter =
          new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
      spinner.setAdapter(adapter);
    }
  }

  private class TextFieldFormHolder extends FormHolder {

    private final EditText editText;

    TextFieldFormHolder(View itemView) {
      super(itemView);
      editText = (EditText) itemView.findViewById(R.id.edit);
    }

    @Override
    public void fill(YamlFormItem formItem) {
      editText.setHint(formItem.hint);
    }
  }

  private class BigTextFieldFormHolder extends FormHolder {

    private final EditText editText;
    private final Toolbar toolbar;

    BigTextFieldFormHolder(View itemView) {
      super(itemView);
      toolbar = (Toolbar) itemView.findViewById(R.id.toolbar);
      editText = (EditText) itemView.findViewById(R.id.edit);
    }

    @Override
    public void fill(YamlFormItem formItem) {
      editText.setHint(formItem.hint);

      YamlFormItemExtras extras = formItem.extras;
      if (extras != null) {
        Menu menu = toolbar.getMenu();
        if (extras.images) {
          MenuItem item = menu.add(0, R.id.form_big_text_item_images, 0, R.string.form_big_text_item_images);
          item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        if (extras.source) {
          MenuItem item = menu.add(0, R.id.form_big_text_item_source, 0, R.string.form_big_text_item_source);
          item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        if (extras.emojis) {
          MenuItem item = menu.add(0, R.id.form_big_text_item_emojis, 0, R.string.form_big_text_item_emojis);
          item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
      }
    }
  }

  private class EmptyFormHolder extends FormHolder {

    EmptyFormHolder(View itemView) {
      super(itemView);
    }

    @Override
    public void fill(YamlFormItem formItem) {

    }
  }

  abstract class FormHolder extends RecyclerView.ViewHolder {

    FormHolder(View itemView) {
      super(itemView);
    }

    public abstract void fill(YamlFormItem formItem);
  }
}
