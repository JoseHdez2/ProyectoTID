
class Parseador

  def self.leer(ruta)
    # Cargamos el contenido
    contents = File.open(ruta, "rb").read
    # Devolvemos el array
    return contents.split
  end

  def self.parsear(ruta, expresion)
    # Cargamos el contenido
    contents = File.open(ruta, "rb").read
    # Devolvemos el array
    return contents.scan(expresion)
  end

end
