
def escribir_weka(rutaWeka, series, generos)

  # contenidoWeka = String que será el contenido del archivo.
  contenidoWeka = ""

  # Colocamos lo que viene a ser el "título" del archivo Weka.
  contenidoWeka += "@RELATION series"

  # Pasamos a escribir los atributos que tendrá cada caso:

  # El nombre de cada serie será un atributo.
  contenidoWeka += "\n@ATTRIBUTE nombreSerie STRING"

  # El número de tropes de cada serie será un atributo.
  contenidoWeka += "\n@ATTRIBUTE numeroTropes NUMERIC"

  # El género desconocido (su porcentaje en la serie) será un atributo.
  contenidoWeka += "\n@ATTRIBUTE #{GENERO_DESCONOCIDO} NUMERIC"

  # Cada género (su porcentaje en la serie) será un atributo.
  generos.each { |g| contenidoWeka += "\n@ATTRIBUTE #{g.nombre} NUMERIC" }

  # Declaramos que empezaremos a introducir la población de datos.
  contenidoWeka += "\n@DATA"

  # Por cada serie, escribimos una línea que la representa:
  series.each do |serie|
    # Abrimos una nueva línea.
    contenidoWeka += "\n"
    # Apuntamos el nombre de la serie.
    contenidoWeka += "#{serie.nombre},"
    # Apuntamos su número de tropes.
    contenidoWeka += "#{serie.tropes.size},"

    # Apuntamos el conteo del género desconocido.
    contenidoWeka += "#{serie.conteoGeneros[GENERO_DESCONOCIDO] / serie.tropes.size.to_f},"

    # Por cada género, apuntamos su conteo:
    generos.each do |g|
      if serie.tropes.size > 0
        contenidoWeka += "#{serie.conteoGeneros[g.nombre] / serie.tropes.size.to_f},"
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
