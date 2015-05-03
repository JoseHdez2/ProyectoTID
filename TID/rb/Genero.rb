load "ParseadorTropes.rb"

# Igual a Serie.rb, solo que sin conteoGeneros.
class Genero

  def initialize(dirArchivoHTML)

    # Nombre de la serie
    # Le asignamos el nombre del archivo como nombre.
    @name = File.basename(dirArchivoHTML, File.extname(dirArchivoHTML))

    # Array con cada uno de los tropes de la serie.
    # Obtenemos el array de tropes al parsear el HTML.
    @tropes = ParseadorTropes.parsearArchivo(dirArchivoHTML)

  end

end
