load "ParseadorTropes.rb"

class Serie

  def initialize(dirArchivoHTML)

    # Nombre de la serie
    # Le asignamos el nombre del archivo como nombre.
    @name = File.basename(dirArchivoHTML, File.extname(dirArchivoHTML))

    # Array con cada uno de los tropes de la serie.
    # Obtenemos el array de tropes al parsear el HTML.
    @tropes = ParseadorTropes.parsearArchivo(dirArchivoHTML)

    # Hash con el conteo de los tropes de cada serie.
    # El valor que retornará por defecto para cualquier clave será 0.
    @conteoGeneros = Hash.new(0)

  end

end
