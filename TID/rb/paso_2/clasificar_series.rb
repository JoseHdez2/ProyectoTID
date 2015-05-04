
def clasificar_series(series, clasificacion_series)
  # Asignamos a cada serie el genero que le asigna el hash "clasificacion_series".
  series.each { |s| s.genero = clasificacion_series[s.nombre]}

  # Retornamos las series ya clasificadas.
  return series
end

# Se da la opcion a pasar un Hash, para combinar metodos de clasificacion.
# Asumimos que las series tienen genero asignado.
# Obtenemos hash con asociaciones { trope => genero }.
def clasificar_tropes_usando_series(series, hash = Hash.new(GENERO_DESCONOCIDO))

  # hash: { trope => { genero => conteo } }
  # ...Es un hash anidado!!!

  hash = Hash.new { |h,k| h[k] = Hash.new(0) }

  series.each do |serie|
    serie.tropes.each do |trope|
      # Opcion 1: Ponemos a verdadero (Ignoramos multiples asignaciones)
      #hash[trope][serie.genero] = true;
      # Opcion 2: Incrementamos el contador (Contamos multiples asignaciones)
      hash[trope][serie.genero] = hash[trope][serie.genero] + 1
      # Opcion 3: Usar opcion 2 y luego decidir si ignorar las asignaciones o no.
    end
  end

  binding.pry
  return hash
end

def reclasificar_series_usando_tropes(series, master_hash_tropes)

end
