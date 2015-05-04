class GeneradorWeka
  #
  # 6to paso: Generar archivo Weka
  #
  def self.escribir(rutaWeka, series, generos)

    # contenidoWeka = String que será el contenido del archivo.
    contenidoWeka = ""

    # Colocamos lo que viene a ser el "título" del archivo Weka.
    contenidoWeka += "@RELATION series"

    # Pasamos a escribir los atributos que tendrá cada caso:

    # El nombre de cada serie será un atributo.
    contenidoWeka += "\n@ATTRIBUTE nombreSerie"

    # El número de tropes de cada serie será un atributo.
    contenidoWeka += "\n@ATTRIBUTE numeroTropes"

    # El género desconocido (su porcentaje en la serie) será un atributo.
    contenidoWeka += "\n@ATTRIBUTE " + GENERO_DESCONOCIDO

    # Cada género (su porcentaje en la serie) será un atributo.
    generos.each { |g| contenidoWeka += "\n@ATTRIBUTE " + g.nombre }

    # Declaramos que empezaremos a introducir la población de datos.
    contenidoWeka += "\n@DATA"

    # Por cada serie, escribimos una línea que la representa:
    series.each do |serie|
      # Abrimos una nueva línea.
      contenidoWeka += "\n"
      # Apuntamos el nombre de la serie.
      contenidoWeka += serie.nombre + ","
      # Apuntamos su número de tropes.
      contenidoWeka += serie.tropes.size().to_s + ","

      # Apuntamos el conteo del género desconocido.
      contenidoWeka += serie.conteoGeneros[GENERO_DESCONOCIDO].to_s + ","

      # Por cada género, apuntamos su conteo:
      generos.each { |g| contenidoWeka += serie.conteoGeneros[g.nombre].to_s + "," }

      # Quitamos el último caracter (",") de la línea, que sobra.
      contenidoWeka.chop!
    end

    # Escribimos el contenido en el archivo.
    File.write(rutaWeka, contenidoWeka);
    puts "Archivo weka generado!"
  end
end
