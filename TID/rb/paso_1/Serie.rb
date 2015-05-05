
class Serie

  def initialize(nombre, tropes)
    @nombre = nombre  # Nombre de serie / genero.
    @tropes = tropes # Array con los tropes de serie / genero.
    @generos = Hash.new(0)
  end

  def tropes
    @tropes
  end

  def nombre
    @nombre
  end

  def generos
    @generos
  end

  def genero=(string)
    @genero = string
  end

  def puntuacionGeneros
    @puntuacionGeneros
  end

end
