
def clasificar_series (clasificacion_tropes, series)
  clasificacion_series = Hash.new { |h,k| h[k] = Hash.new(0) }

  series.each do |nombre_serie,tropes_serie|

    #Introducimos la serie en la clasificacion
    clasificacion_series[nombre_serie] = Hash.new(0)

    tropes_serie.each do |trope|
      generos_trope = clasificacion_tropes[trope]
      generos_trope.each do |genero,valor|
        clasificacion_series[nombre_serie][genero] = clasificacion_series[nombre_serie][genero] + valor
      end
    end
  end

  # Normalizamos
  clasificacion_series.each_value do |generos_serie|
    # Obtenemos el total de asignaciones que se hicieron a este trope.
    total_score = generos_serie.values.inject(:+)
    # Por cada genero asignado a este trope, normalizamos su valor.
    generos_serie.each do |key,val|
      generos_serie[key] = (val / total_score.to_f).round(DECIMALES_REDONDEO)
    end
  end

end
