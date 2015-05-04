load "Serie.rb"

GENERO_DESCONOCIDO = "Desconocido"

CARPETA_TROPES_RAW = "/home/jose/Documents/tid/raw/series"
CARPETA_TROPES_PRO = "/home/jose/Documents/tid/pro/series"
CARPETA_GENEROS_RAW = "/home/jose/Documents/tid/raw/generos"
CARPETA_GENEROS_PRO = "/home/jose/Documents/tid/pro/generos"
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"


class EstudioSeries

  # La funcion mas importante
  def realizarEstudio()

    series = []  # Array con las series, cada una con sus tropes.
    generos = [] # Array con los géneros, cada uno con sus tropes.

    # Hash con cada trope asignado al género al que pertenece.
	  # Le das el nombre de un trope y te devuelve su género.
    # El valor que devuelve por defecto es GENERO_DESCONOCIDO.
    hashMaestro = Hash.new(GENERO_DESCONOCIDO)

    cargaTropes = "P"
    cargaGeneros = "P"

    preguntarModos()

    # 1er paso: Cargar las series en un array.
		parsearArraySeries(carpetaTropes)

		# 2do paso: Cargar los generos en un array.
		rellenarArrayGeneros(carpetaGeneros)

		# 3er paso: .
		listaMaestra.agregarGeneros(generos)

		# 5to paso: Calcular los conteos de generos por cada serie.
		calcularConteoGeneros()

		# 6to paso: Generar archivo Weka
		generarArchivoWeka(dirArchivoWeka)
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
