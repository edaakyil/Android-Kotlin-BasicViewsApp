
# Basic Views Application


- [Sorular](#sorular)
- [Code snippets examples](#code-snippets-examples)


### Questions




### Code snippets examples

<div style="background-color:#f6f6f9;padding:10px 20px;margin:2em 0;border:solid #eee 1px;">

```kt
val maritalStatus = arrayOf<String>(resources.getString(R.string.single), resources.getString(R.string.married), resources.getString(R.string.divorced)) // redundant
mArrayAdapterMaritalStatus = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, maritalStatus) // redundant
```

```kt
val maritalStatus = arrayOf(resources.getString(R.string.single), resources.getString(R.string.married), resources.getString(R.string.divorced)) //type inference
mArrayAdapterMaritalStatus = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, maritalStatus) //type inference
```

</div>
<div style="background-color:#f6f6f9;padding:10px 20px;margin:2em 0;border:solid #eee 1px;">

```kt
mRandomTextsListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
```
veya
```kt
mRandomTextsListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())
```

</div>
<div style="background-color:#f6f6f9;padding:10px 20px;margin:2em 0;border:solid #eee 1px;">

```kt
mRandomTextsListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
```
Bu kodda `ArrayList<String>()` ile ArrayAdapter'a boş bir liste eklendi. ArrayAdapter'a daha sonradan ekleme yapabilmek için dizi yerine liste kullandık.
```kt
```

</div>
