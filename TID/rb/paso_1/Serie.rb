
class Serie

  def initialize(nombre, tropes)
    @nombre = nombre  # Nombre de serie / genero.
    @tropes = tropes # Array con los tropes de serie / genero.
    @genero = GENERO_DESCONOCIDO

    # Hash con el conteo de los tropes de cada serie.
    # El valor que retornará por defecto para cualquier clave será 0.
    @conteoGeneros = Hash.new(0)
  end

  def tropes
    @tropes
  end

  def nombre
    @nombre
  end

  def genero
    @genero
  end

  def conteoGeneros
    @conteoGeneros
  end

end
