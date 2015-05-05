
def escribir_weka(rutaWeka, series)

  contenidoWeka = "@RELATION series"

  #
  # Atributos
  #Inferimos

  # El nombre de cada serie será un atributo.
  contenidoWeka += "\n@ATTRIBUTE nombreSerie STRING"

  # El número de tropes de cada serie será un atributo.
  contenidoWeka += "\n@ATTRIBUTE numeroTropes NUMERIC"

  # El género desconocido (su porcentaje en la serie) será un atributo.
  contenidoWeka += "\n@ATTRIBUTE #{GENERO_DESCONOCIDO} NUMERIC"

  # Extraemos posibles generos a partir del hash "clasificacion_tropes"
  generos = []
  clasificacion_tropes.each do |trope, hash_generos|
    hash_generos.each_key do |genero|
      generos.push(genero) unless generos.include?(genero)
    end
  end

  # Cada género (su porcentaje en la serie) será un atributo.
  generos.each { |g| contenidoWeka += "\n@ATTRIBUTE #{g} NUMERIC" }

  # Declaramos que empezaremos a introducir la población de datos.
  contenidoWeka += "\n@DATA"

  # Por cada serie, escribimos una línea que la representa:
  series.each do |serie|

    # Skip series without any tropes.
    next if serie.tropes.size == 0

    # Abrimos una nueva línea.
    contenidoWeka += "\n"
    # Apuntamos el nombre de la serie.
    contenidoWeka += "#{serie.nombre},"
    # Apuntamos su número de tropes.
    contenidoWeka += "#{serie.tropes.size},"

    # Apuntamos el conteo del género desconocido.
    contenidoWeka += "#{serie.puntuacionGeneros[GENERO_DESCONOCIDO] / serie.tropes.size.to_f},"

    # Por cada género, apuntamos su puntuacion:
    generos.each do |g|
      if serie.tropes.size > 0
        contenidoWeka += "#{serie.puntuacionGeneros[g] / serie.tropes.size.to_f},"
      else
        contenidoWeka += "0,"
      end
    end

    # Quitamos el último caracter (",") de la línea, que sobra.
    contenidoWeka.chop!
  end

  # Escribimos el contenido en el archivo.
  File.write(rutaWeka, contenidoWeka);
  puts "Archivo weka generado!"
end
