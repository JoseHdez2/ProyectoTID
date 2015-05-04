
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

  # masterList: { trope => { genero => conteo } }
  # ...Es un hash anidado!!!
  masterList = Hash.new(Hash.new(0))

  series.each do |serie|
    serie.each.tropes do |trope|
      # Incrementamos el contador
      masterList[trope][serie.genero] = masterList[trope][serie.genero] + 1
    end
  end

  # Tenemos los conteos, ahora viene la decision.
  # El genero de cada trope sera el que mas veces se le haya asignado.
  masterList.each do |trope,hashConteo|
    mayorGenero = GENERO_DESCONOCIDO
    mayorConteo = 0
    hashConteo.each do |genero,conteo|
      # Si tiene un conteo mayor, se le adjudica.
      if conteo > mayorConteo
        mayorGenero = genero
        mayorConteo = conteo
      end
    end
    # Asignamos el genero ganador.
    hash[trope] = mayorGenero
  end
  binding.pry
  return hash
end
