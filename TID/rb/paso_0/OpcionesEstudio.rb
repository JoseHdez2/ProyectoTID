
CARPETA_TROPES_RAW = "/home/jose/Documents/tid/raw/series"
CARPETA_TROPES_PRO = "/home/jose/Documents/tid/pro/series"
CARPETA_GENEROS_RAW = "/home/jose/Documents/tid/raw/generos"
CARPETA_GENEROS_PRO = "/home/jose/Documents/tid/pro/generos"
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"

PREGUNTAR = false

class OpcionesEstudio
  def initialize()
    @cargaTropes = :PARSE
    @cargaGeneros = :PARSE
    @carpetaTropes = CARPETA_TROPES_RAW
    @carpetaGeneros = CARPETA_GENEROS_RAW
    @rutaWeka = ARCHIVO_WEKA

    if PREGUNTAR
      self.preguntarOpciones()
    end
  end

  def carpetaTropes
    @carpetaTropes
  end

  def carpetaGeneros
    @carpetaGeneros
  end

  def rutaWeka
    @rutaWeka
  end

  def confirmarOpciones()
    bien = false
    while bien == false do

      # Preguntamos por los modos y carpetas
      preguntarOpciones()

      # Preguntar si todo bien, para salir del bucle.
      answer = preguntar("Bien asi? [S]i, [N]o ")
      bien = (answer == "S") ? true : false
    end
  end

  def preguntarOpciones()

      # Tropes
      # Preguntar por el modo de carga
      answer = preguntar("Tropes: [P]arseado o [L]ectura? ")
      @cargaTropes = (answer == "L") ? :READ : :PARSE

      @carpetaTropes = ( @cargaTropes == :READ) ? CARPETA_TROPES_PRO : CARPETA_TROPES_RAW

      # Preguntar por la carpeta de carga de tropes.
      @carpetaTropes = confirmar("carpeta de carga de tropes", @carpetaTropes)

      # Series
      # Preguntar por el modo de carga
      answer = preguntar("Generos: [P]arseado o [L]ectura? ")
      @cargaGeneros = (answer == "L") ? :READ : :PARSE

      @carpetaGeneros = ( @cargaGeneros == :READ) ? CARPETA_GENEROS_PRO : CARPETA_GENEROS_RAW

      # Preguntar por la carpeta de carga de generos.
      @carpetaGeneros = confirmar("carpeta de carga de generos", @carpetaGeneros)

    end

end
