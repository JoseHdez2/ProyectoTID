class ClasificadorTropes
  def self.darClasificacion(clases, valor_defecto)
    hash = Hash.new(valor_defecto)
    clases.each do |clase|
      clase.tropes.each do |trope|
        if hash[trope] != valor_defecto
          puts "Se intento #{trope} => #{clase.nombre}, cuando ya => #{hash[trope]}"
        else
          hash[trope] = clase.nombre
        end
      end
    end
    return hash
  end

  def self.darConteo(conjuntos, clasificacion)
    # Por cada serie,
    # por cada uno de sus tropes
    conjuntos.each do |serie|
      serie.tropes.each do |trope|

        # 'Cargamos' el genero del trope.
        generoDelTrope = clasificacion[trope]

        # Incrementamos al conteo de dicho genero en esta serie.
        serie.conteoGeneros[generoDelTrope] = serie.conteoGeneros[generoDelTrope] + 1
      end
    end
    return conjuntos
  end
end
