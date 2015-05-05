def escribir_weka(rutaWeka, clasificacion_series)
  contenidoWeka = "@RELATION series"
  contenidoWeka += "\n@ATTRIBUTE nombreSerie STRING"
  contenidoWeka += "\n@ATTRIBUTE #{GENERO_DESCONOCIDO} NUMERIC"

  # Inferimos todos los generos
  generos = []
  clasificacion_series.each do |nombre_serie, generos_serie|
    generos_serie.each_key do |nombre_genero|
      generos.push(nombre_genero) unless generos.include?(nombre_genero)
    end
  end
  generos.delete(GENERO_DESCONOCIDO)
  generos.sort!

  #cada genero sera un atributo
  generos.each { |g| contenidoWeka += "\n@ATTRIBUTE #{g} NUMERIC"}

  # Declaramos que empezaremos a introducir la población de datos.
  contenidoWeka += "\n@DATA"

  series = clasificacion_series.keys.sort!

  series.each do |serie|
    contenidoWeka += "\n"
    contenidoWeka += "#{serie},"
    contenidoWeka += "#{clasificacion_series[serie][GENERO_DESCONOCIDO]},"
    generos.each { |g| contenidoWeka += "#{clasificacion_series[serie][g]}," }
    contenidoWeka.chop! #Quitamos el último caracter (",")
  end

  File.write(rutaWeka, contenidoWeka)
  puts "Archivo Weka generado! Sin colisiones!"
end
