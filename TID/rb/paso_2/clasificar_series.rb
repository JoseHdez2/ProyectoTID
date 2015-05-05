
def clasificar_series(series, clasificacion_series)
  # Asignamos a cada serie el genero que le asigna el hash "clasificacion_series".
  series.each { |s| s.genero = clasificacion_series[s.nombre]}

  # Retornamos las series ya clasificadas.
  return series
end

def reclasificar_series_usando_tropes(series, hash_tropes)
  series.each do |serie|
    serie.tropes.each do |trope|
      puntosTrope = 0
      hash_tropes[trope].each_value do |conteoGenero|
        puntosTrope += conteoGenero
      end
      binding.pry
      hash_tropes[trope].each do |genero,conteo|
        serie.puntuacionGeneros[genero] += conteo / puntosTrope
      end
    end
  end
end
