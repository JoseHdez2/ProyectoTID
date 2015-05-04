
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
