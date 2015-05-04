
CARPETA_TROPES_RAW = "/home/jose/Documents/tid/raw/series"
CARPETA_TROPES_PRO = "/home/jose/Documents/tid/pro/series"
CARPETA_GENEROS_RAW = "/home/jose/Documents/tid/raw/generos"
CARPETA_GENEROS_PRO = "/home/jose/Documents/tid/pro/generos"
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"

class OpcionesEstudio
  def initialize()
    @cargaTropes = :PARSE
    @cargaGeneros = :PARSE
    @carpetaTropes = CARPETA_TROPES_RAW
    @carpetaGeneros = CARPETA_GENEROS_RAW
    @rutaWeka = ARCHIVO_WEKA

    self.preguntarOpciones()
  end

  def preguntarOpciones()

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
end
