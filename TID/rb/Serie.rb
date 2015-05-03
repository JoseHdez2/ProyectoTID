load "ParseadorTropes.rb"

class Serie

  def initialize(dirArchivoHTML, modo)

    # Nombre de la serie
    # Le asignamos el nombre del archivo como nombre.
    @name = File.basename(dirArchivoHTML, File.extname(dirArchivoHTML))

    # Array con cada uno de los tropes de la serie.
    @tropes = []

    if (modo == "L")
      @tropes = ParseadorTropes.leerArchivo(dirArchivoHTML)
    else
      @tropes = ParseadorTropes.parsearArchivo(dirArchivoHTML)
    end

    # Hash con el conteo de los tropes de cada serie.
    # El valor que retornará por defecto para cualquier clave será 0.
    @conteoGeneros = Hash.new(0)

  end

end
