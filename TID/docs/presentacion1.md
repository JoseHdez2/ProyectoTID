Al principio, cada trope solo podía pertenecer a un único género.

Supongamos que tenemos el trope *AbandonedMansion*.

```ruby
clasificacion_tropes = {
  "AbandonedMansion" => "Mystery"
}
```

Pero *tvTropes* es una wiki: cada trope puede aparecer en más de un género.

Si ocurre que un trope aparece en más de un género...:
```ruby
clasificacion_tropes["AbandonedMansion"] = "Horror"
```

```ruby
"Error! 'AbandonedMansion' already has value 'Mystery'"
```

Esto provoca que *AbandonedMansion* tenga un único género posible, en vez de los varios que pudiese tener.

---

**Para solucionarlo**, lo que hacemos es llevar un contador por cada genero al que pertenezca.

```ruby
clasificacion_tropes = {
  "AbandonedMansion" => {"Mystery" => 1}
}
```
Y a la hora de agregar otro género, no habrá colisión:
```ruby
clasificacion_tropes["AbandonedMansion"] = "Horror"
```
```ruby
clasificacion_tropes = {
  "AbandonedMansion" => {
    "Mystery" => 0.5,
    "Horror" => 0.5
  }
}

```
Al final, el trope podrá pertenecer a muchos géneros y no habrá pérdida de información.
