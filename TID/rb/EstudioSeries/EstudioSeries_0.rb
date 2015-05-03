
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

CARPETA_TROPES_RAW = "/home/jose/Documents/tid/raw/series"
CARPETA_TROPES_PRO = "/home/jose/Documents/tid/pro/series"
CARPETA_GENEROS_RAW = "/home/jose/Documents/tid/raw/generos"
CARPETA_GENEROS_PRO = "/home/jose/Documents/tid/pro/generos"
ARCHIVO_WEKA = "/home/jose/Documents/tid/weka/tropes.arff"

class OpcionesEstudio

  def initialize()
    @cargaTropes = "P"
    @cargaGeneros = "P"
    @carpetaTropes = CARPETA_TROPES_RAW
    @carpetaGeneros = CARPETA_GENEROS_RAW
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

      @carpetaTropes = ( @cargaTropes == "P") ? CARPETA_GENEROS_RAW : CARPETA_GENEROS_PRO

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
end
