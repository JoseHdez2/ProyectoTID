def clasificar_tropes(series, generos, clasificacion_tropes = Hash.new { |h,k| h[k] = Hash.new(0) })

  # Introducimos todos los tropes de todas las series en la clasificacion:
  series.each_value do |tropes_de_serie|
    tropes_de_serie.each do |trope|
      # Si no existia el trope en la clasificacion, crearlo.
      clasificacion_tropes[trope] = Hash.new(0) if not clasificacion_tropes.key(trope)
    end
  end

  # Pasamos a dar clasificacion a los tropes, que ya estan en la clasificacion.

  generos.each do |g_name, g_tropes|
    g_tropes.each do |g_trope|
      clasificacion_tropes[g_trope][g_name] = clasificacion_tropes[g_trope][g_name]+ 1
    end
  end
  # Si para algun trope no hay generos asignados, asignar GENERO_DESCONOCIDO.

  clasificacion_tropes.each_value do |generos|
    generos[GENERO_DESCONOCIDO] = 1 if generos.empty?
  end

  # Terminamos por normalizar los generos de los tropes,
  # para que los valores de genero de cada trope siempre sumen 1.

  clasificacion_tropes.each_value do |generos_trope|
    # Obtenemos el total de asignaciones que se hicieron a este trope.
    total_score = generos_trope.values.inject(:+)
    # Por cada genero asignado a este trope, normalizamos su valor.
    generos_trope.each_value { |score| score = score / total_score.to_f }
    binding.pry
  end
end
