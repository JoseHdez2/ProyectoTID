load "Serie.rb"

GENERO_DESCONOCIDO = "Desconocido"

CARPETA_TROPES_RAW = "/home/jose/Documents/tid/raw/series"
CARPETA_TROPES_PRO =
CARPETA_GENEROS_RAW = "/home/jose/Documents/tid/raw/generos"
CARPETA_GENEROS_PRO =
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"


class EstudioSeries
  def initialize()
    # Array con las series, cada una con sus tropes.
    @series = []
    # Array con los géneros, cada uno con sus tropes.
    @generos = []

    # Hash con cada trope asignado al género al que pertenece.
	  # Le das el nombre de un trope y te devuelve su género.
    # El valor que devuelve por defecto es GENERO_DESCONOCIDO.
    @hashMaestro = Hash.new(GENERO_DESCONOCIDO)

    # Modo. :P => parseado, :L => lectura
    @cargaTropes = "P"
    @cargaGeneros = "P"

    # Carpetas donde buscar los archivos
    @carpetaTropes = CARPETA_TROPES_RAW
    @carpetaGeneros = CARPETA_GENEROS_RAW
    @dirArchivoWeka = DIR_ARCHIVO_WEKA
  end

  # La funcion mas importante
  def realizarEstudio(carpetaTropes, carpetaGeneros, dirArchivoWeka)

    preguntarModos()

    # 1er paso: Rellenar el array de series.
		parsearArraySeries(carpetaTropes)

		# 2do paso: Agregar los tropes a la lista maestra.
    # No se realiza.
		#listaMaestra.agregarTropes(series)

		# 3er paso: Rellenar el array de generos.
		rellenarArrayGeneros(carpetaGeneros)

		# 4to paso: Agregar los generos a la lista maestra.
		listaMaestra.agregarGeneros(generos)

		# 5to paso: Calcular los conteos de generos por cada serie.
		calcularConteoGeneros()

		# 6to paso: Generar archivo Weka
		generarArchivoWeka(dirArchivoWeka)
  end

  def preguntar(pregunta)
    # Inferimos las posibles respuestas
    posiblesRespuestas = pregunta.scan(/(?<=\[).*?(?=\])/)

    # Si no hay respuestas predefinidas, se da libre eleccion.
    if posiblesRespuestas.empty
      p pregunta + ":"
      return gets.chomp
    end

    # Pasamos las respuestas a mayusculas
    posiblesRespuestas.collect! { |resp| resp.upcase() }
    answer = ""

    # Mientras la respuesta no coincida con niguna de las posibles...
    while posiblesRespuestas.include?(answer) == false do
      p pregunta + ":"
      answer = gets.chomp.upcase
    end
    return answer
  end

  def preguntarModos()
    bien = false
    while bien == false do

      # Preguntar por el modo de carga de tropes
      @cargaTropes = preguntar("Tropes: [P]arseado o [L]ectura? ")

      @carpetaTropes = ( @cargaTropes == "P") ? CARPETA_TROPES_RAW : CARPETA_TROPES_PRO

      # Preguntar por la carpeta de carga de tropes.
      puts "Carpeta de carga de tropes: " + @carpetaTropes
      if preguntar("Cambiar carpeta? [S]i, [N]o ") == "S"
        @carpetaTropes = preguntar("Nueva carpeta")
      end

      # Preguntar por la carpeta de carga de tropes.
      puts "Carpeta de carga de tropes: " + @carpetaTropes
      if preguntar("Cambiar carpeta? [S]i, [N]o ") == "S"
        @carpetaTropes = preguntar("Nueva carpeta")
      end

      # Preguntar por el modo de carga de generos.
      @cargaGeneros = preguntar("Generos: [P]arseado o [L]ectura? ")

      # Preguntar por la carpeta de carga de tropes.
      puts "Carpeta de carga de generos: " + @carpetaGeneros
      if preguntar("Cambiar carpeta? [S]i, [N]o ") == "S"
        @carpetaTropes = preguntar("Nueva carpeta")
      end

      # Preguntar si todo bien, para salir del bucle.
      answer = preguntar("Bien asi? [S]i, [N]o ")
      bien = (answer == "S") ? true : false
    end
  end

  #
  # 1er paso: Rellenar el array de series.
  #
  def rellenarArraySeriesParseando(carpetaTropes)

    # Colocamos las dir de los HTML en un array.
    dirsSeries = Dir[carpetaTropes+"/*.html"]

    # Ordenamos los archivos alfabéticamente.
    dirsSeries.sort!

    # Por cada archivo,
    # crear una serie y agregarla al array.

    seriesCount = 0
    dirsSeries.each do |dir|
      @series.push(Serie.new(dir))
      seriesCount += 1
      puts "series cargadas: #{seriesCount}"
    end
  end
  #
  # 1er paso: Rellenar el array de series.
  #
  def rellenarArraySeriesLeyendo(carpetaTropes)

    # Colocamos las dir de los HTML en un array.
    dirsSeries = Dir[carpetaTropes+"/*.html"]

    # Ordenamos los archivos alfabéticamente.
    dirsSeries.sort!

    # Por cada archivo,
    # crear una serie y agregarla al array.

    seriesCount = 0
    dirsSeries.each do |dir|
      @series.push(Serie.new(dir))
      seriesCount += 1
      puts "progress: #{seriesCount}%"
    end
  end

  #
  # 2do paso: Agregar los tropes al hash maestro. Ya no se realiza.
  #

  #
  # 3er paso: Rellenar el array de generos.
  #
  def rellenarArrayGeneros(carpetaGeneros)

    # Colocamos las dir de los HTML en un array.
    dirsSeries = File[carpetaGeneros+"/*.html"]

    # Ordenamos los archivos alfabéticamente.
    dirsSeries.sort!

    # Por cada archivo,
    # crear un género y agregarlo al array.
    dirSeries.each do |dir|
      @series.push(Genero.new(dir))
    end
  end

  #
  # 4to paso: Agregar los generos al hash maestro.
  #
  def agregarGeneros()
    # Por cada genero,
    # por cada uno de sus tropes
    @generos.each do |genero|
      genero.tropes.each do |trope|

        # Si ya fue asignado otro género
        if @hashMaestro[trope] != GENERO_DESCONOCIDO
          # Avisamos (y no reasignamos)
          puts "Se quiso asignar a #{trope} el genero #{genero}, cuando ya era #{prevGen}."
        else
          # Asignamos el género
          @hashMaestro[trope] = genero.name
        end
      end
    end
  end

  #
  # 5to paso: Calcular los conteos de generos por cada serie.
  #
  def calcularConteoGeneros()
    # Por cada serie,
    # por cada uno de sus tropes
    @series.each do |serie|
      serie.tropes.each do |trope|

        # 'Cargamos' el genero del trope.
        generoDelTrope = @hashMaestro[trope]

        # Incrementamos al conteo de dicho genero en esta serie.
        serie.conteoGeneros[generoDelTrope] = serie.conteoGeneros[generoDelTrope] + 1
      end
    end
  end

  #
  # 6to paso: Generar archivo Weka
  #
  def generarArchivoWeka(dirArchivoWeka)

    # wekaContent = String que será el contenido del archivo.
		# Colocamos lo que viene a ser el "título" del archivo Weka.
		wekaContent += "@RELATION series"

		# Pasamos a escribir los atributos que tendrá cada caso:

		# El nombre de cada serie será un atributo.
		wekaContent += "\n@ATTRIBUTE nombreSerie"

		# El número de tropes de cada serie será un atributo.
		wekaContent += "\n@ATTRIBUTE numeroTropes"

		# El género desconocido (su porcentaje en la serie) será un atributo.
		wekaContent += "\n@ATTRIBUTE " + GENERO_DESCONOCIDO

		# Cada género (su porcentaje en la serie) será un atributo.
		@generos.each { |g| wekaContent += "\n@ATTRIBUTE " + g.name }

		# Declaramos que empezaremos a introducir la población de datos.
		wekaContent += "\n@DATA"

		# Por cada serie, escribimos una línea que la representa:
    @series.each do |serie|
			# Abrimos una nueva línea.
			wekaContent += "\n"
			# Apuntamos el nombre de la serie.
			wekaContent += serie.name + ","
		  # Apuntamos su número de tropes.
			wekaContent += serie.tropes.size() + ","

			# Apuntamos el conteo del género desconocido.
			wekaContent += serie.conteoGeneros[GENERO_DESCONOCIDO] + ","

			# Por cada género, apuntamos su conteo:
			@generos.each { |g| wekaContent += serie.conteoGeneros[g.name] + "," }

			# Quitamos el último caracter (",") de la línea, que sobra.
			wekaContent.chop!
		end

    # Escribimos el contenido en el archivo.
		File.write(dirArchivoWeka, wekaContent);
	end
end
