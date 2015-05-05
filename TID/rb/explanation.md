**tropes_series**
```ruby
{
  "Arrow" => ["AbandonedWarehouse", "LoveHurts"]
}
```
**clasificacion_tropes**

Hash que clasifica todos los tropes.
Cada trope puede tener más de un género, en proporciones distintas.
```ruby
{
  "AbandonedWarehouse" => { "Action" => 1, "Mystery" => 1}
  "LoveHurts" => { "Action" => 1, "Romance" => 2 }
}
```
**Serie clasificada**
```ruby
nombre = "Arrow"
tropes = ["AbandonedWarehouse", "LoveHurts"]
generos = {"Action" => 1.3, "Mystery" => 0.5,
          "Romance" => 0.6}
```
